package com.it_cgm.service;

import com.it_cgm.model.CharacterMetadata;
import com.it_cgm.model.OverallStatisticsHolder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CGMService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CGMService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private final Object writeLock = new Object();

    private static final Pattern LETTERS_PATTERN = Pattern.compile("[^a-zA-Za-åa-ö-w-я]");

    private final Map<String, OverallStatisticsHolder> statsHolding = new ConcurrentHashMap<>();

    /**
     * анализ строки.
     *
     * @param text строка на входе
     * @return строка json на выходе c информациеи о каждой встреченной в строке букве.
     **/
    public final String analyseSequence(String text) {

        final String validString = text.replaceAll(LETTERS_PATTERN.toString(), "");

        // Universally unique generated identifier to bind each CharacterMetadata
        // to its history stored inside OverallStatisticsHolder object
        final UUID uuid = UUID.randomUUID();

        final OverallStatisticsHolder holder = new OverallStatisticsHolder();
        holder.setText(validString);

        final Map<Character, CharacterMetadata> metadata = new HashMap<>();

        int pos = 0;
        for (char c : validString.toCharArray()) {
            if (!metadata.containsKey(c)) {
                final CharacterMetadata data = new CharacterMetadata();
                data.setCh(c);
                data.setPosition(pos++);
                metadata.put(c, data);
            } else {
                final CharacterMetadata data = metadata.get(c);
                data.setCounter(data.getCounter() + 1);
            }
        }

        final List<CharacterMetadata> finalizedData = metadata.values().stream()
                .sorted(Comparator.comparingLong(CharacterMetadata::getPosition))
                .collect(Collectors.toList());

        holder.setRequestUuid(uuid.toString());
        holder.setData(finalizedData);

        // We are adding a critical section below to prevent race condition on write operation;
        synchronized (writeLock) {
            statsHolding.put(uuid.toString(), holder);
        }

        // два числа по каждой букве: количество букв в строке и длина максимальной непрерывной последовательности из этой буквы, которая встретилась в строке

        @Data
        class SequenceStatsHolder {
            @JsonProperty("number_of_letters")
            private long maxNumberOfCharacters;

            @JsonProperty("sequence_length")
            private long maxSequenceLength;

            @JsonProperty
            private List<CharacterMetadata> data;
        }

        final SequenceStatsHolder stats = new SequenceStatsHolder();
        stats.setMaxNumberOfCharacters(validString.length()); // количество букв в строке
        stats.setMaxSequenceLength(text.length());
        stats.setData(finalizedData);

        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            LOGGER.error("unable to convert model to a json string; {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * статистика по запросам.
     *
     * @return строка json на выходе c статистикой о каждоm запросe.
     **/
    public final String gatherStatistics() {

        @Data
        class TmpStatsHolder {
            @JsonProperty
            private String requestId;
            @JsonProperty
            private String averageOfMaxLength;
        }

        @Data
        class RequestStatsHolder {
            @JsonProperty
            private List<TmpStatsHolder> statistics;
        }

        final List<TmpStatsHolder> tmpList = new ArrayList<>();

        // for each stored request
        for (val e : statsHolding.entrySet()) {
            final OverallStatisticsHolder holder = e.getValue();

            final float maxSum = CGMService.maxSum(holder.getData().stream().map(CharacterMetadata::getCounter).iterator());
            final int divisor = holder.getData().size();

            float average = maxSum / divisor; // средняя длина максимальной последовательности

            final TmpStatsHolder statsHolder = new TmpStatsHolder();
            statsHolder.setRequestId(e.getKey());
            statsHolder.setAverageOfMaxLength(BigDecimal.valueOf(average).toPlainString());
            tmpList.add(statsHolder);
        }

        final RequestStatsHolder requestStats = new RequestStatsHolder();
        requestStats.setStatistics(tmpList);

        try {
            return mapper.writeValueAsString(requestStats);
        } catch (JsonProcessingException e) {
            LOGGER.error("unable to convert model to a json string; {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // A static method to calculate the Max Length
    private static float maxSum(Iterator<Integer> first) {
        float currentMax = 0, maxEnding = 0;
        int f = first.next();
        for (; first.hasNext(); f = first.next()) {
            maxEnding = Math.max(maxEnding + f, 0);
            currentMax = Math.max(currentMax, maxEnding);
        }
        return currentMax;
    }

    public static void main(String[] args) {
        final CGMService service = new CGMService();
        service.analyseSequence("kdsfl;adsfkalnkl;d2930e23e-230i302");
        service.gatherStatistics();
    }
}

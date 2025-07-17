package Util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public final class ConcurrentUtils {

    private static final int DEFAULT_CONCURRENCY = 2;

    private ConcurrentUtils() {

    }

    public static <T, R> List<R> execute(List<T> dataList, Function<T, R> function, ExecutorService executorService) {
        return ConcurrentUtils.execute(dataList, function, executorService, DEFAULT_CONCURRENCY);
    }

    public static <T, R> List<R> execute(List<T> dataList, Function<T, R> function, ExecutorService executorService, int concurrency) {
        if (CollectionUtils.isEmpty(dataList)) {
            return Lists.newArrayList();
        }
        if (dataList.size() == 1) {
            return Lists.newArrayList(function.apply(dataList.get(0)));
        }
        return Flux.fromIterable(dataList).flatMapSequential(data -> Mono.fromCallable(() -> function.apply(data))
                        .subscribeOn(Schedulers.fromExecutorService(executorService))
                        .doOnError(throwable -> log.error("Flux.fromIterable(dataList).flatMap", throwable)), concurrency)
                .filter(Objects::nonNull)
                .collectList().block();
    }

    public static <T> void execute(List<T> dataList, Consumer<T> consumer, ExecutorService executorService) {
        ConcurrentUtils.execute(dataList, consumer, executorService, DEFAULT_CONCURRENCY);
    }

    public static <T> void execute(List<T> dataList, Consumer<T> consumer, ExecutorService executorService, int concurrency) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        if (dataList.size() == 1) {
            consumer.accept(dataList.get(0));
            return;
        }
        Flux.fromIterable(dataList).flatMap(data -> Mono.fromRunnable(() -> consumer.accept(data))
                        .subscribeOn(Schedulers.fromExecutorService(executorService))
                        .doOnError(throwable -> log.error("Flux.fromIterable(dataList).flatMap", throwable)), concurrency)
                .blockLast();
    }

}
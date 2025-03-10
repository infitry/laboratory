package com.infitry.laboratory.service.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class ThreadService {
    /**
     * platform thread
     */
    public void platformThread() {
        final var loopCount = 100;
        final var threadCount = 50;
        var executor = Executors.newFixedThreadPool(threadCount);
        var futures = IntStream.range(0, loopCount)
            .mapToObj(i -> CompletableFuture.runAsync(ThreadService::doHeavyTask, executor))
            .toList();

        var allOf = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        allOf.join();
    }

    /**
     * virtual thread
     */
    public void virtualThread() {
        final var loopCount = 100;
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var futures = IntStream.range(0, loopCount)
                .mapToObj(i -> CompletableFuture.runAsync(ThreadService::doHeavyTask, executor))
                .toList();

        var allOf = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        allOf.join();
    }

    private static void doHeavyTask() {
        log.info("실행 중 : {}", Thread.currentThread().getName());
        sleep(3000);
        log.info("완료 : {}", Thread.currentThread().getName());
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignore) {}
    }

    /**
     * main
     */
    public static void main(String[] args) {
        log.info("Available processors : {}", Runtime.getRuntime().availableProcessors());
        var threadType = ThreadType.PLATFORM; // 변경하여 실행 해보기

        ThreadService threadService = new ThreadService();
        switch (threadType) {
            case PLATFORM:
                threadService.platformThread();
                break;
            case VIRTUAL:
                threadService.virtualThread();
                break;
        }
    }

    public enum ThreadType {
        PLATFORM, VIRTUAL
    }
}

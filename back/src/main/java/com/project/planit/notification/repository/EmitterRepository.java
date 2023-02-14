package com.project.planit.notification.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class EmitterRepository {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    public SseEmitter save(String id, SseEmitter sseEmitter) {
        emitters.put(id, sseEmitter);
        return sseEmitter;
    }

    public void saveEventCache(String id, Object event) {
        eventCache.put(id, event);
    }

    public Map<String, SseEmitter> findAllStartWithById(String id) {
//        System.out.println(emitters.entrySet().stream());
//        System.out.println(emitters.entrySet().stream()
//                .filter(entry -> entry.getKey().startsWith(id)));
//        System.out.println(emitters.entrySet().stream()
//                .filter(entry -> entry.getKey().startsWith(id))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Object> findAllEventCacheStartWithId(String id) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void deleteAllStartWithId(String id) {
        emitters.forEach(
                (key, emitter) -> {
                    if (key.startsWith(id)) {
                        emitters.remove(key);
                    }
                }
        );
    }

    public void deleteById(String id) {
        emitters.remove(id);
    }

    public void deleteAllEventCacheStartWithId(String id) {
        eventCache.forEach(
                (key, data) -> {
                    if (key.startsWith(id)) {
                        eventCache.remove(key);
                    }
                }
        );
    }

}
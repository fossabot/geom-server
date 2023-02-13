package io.github.xezzon.geom.auth.observation;

import io.github.xezzon.tao.observer.Observation;

/**
 * 用户注册事件
 * @author xezzon
 */
public record UserRegisterObservation(
    String userId,
    String username,
    String nickname
) implements Observation {
}

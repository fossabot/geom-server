package indi.xezzon.geom.auth.observation;

import indi.xezzon.tao.observer.Observation;

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

package indi.xezzon.geom.auth.domain;

import static indi.xezzon.geom.auth.domain.UserGroupMember.GROUP_ID;
import static indi.xezzon.geom.auth.domain.UserGroupMember.USER_ID;

import indi.xezzon.geom.auth.constant.DatabaseConstant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户组成员
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(
    name = "system_user_group_member",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {GROUP_ID, USER_ID})
    }
)
@Entity
public class UserGroupMember {

  static final String GROUP_ID = "group_id";
  static final String USER_ID = "user_id";

  /**
   * 用户组成员主键
   */
  @Id
  @Column(name = "id", nullable = false, updatable = false, length = DatabaseConstant.ID_LENGTH)
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @GeneratedValue(generator = "uuid")
  private String id;
  /**
   * 用户组主键
   */
  @Column(name = GROUP_ID, nullable = false, updatable = false, length = DatabaseConstant.ID_LENGTH)
  private String groupId;
  /**
   * 用户主键
   */
  @Column(name = USER_ID, nullable = false, updatable = false, length = DatabaseConstant.ID_LENGTH)
  private String userId;

  /**
   * 用户组信息
   */
  @Transient
  private UserGroup group;
  /**
   * 用户信息
   */
  @Transient
  private User user;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserGroupMember that = (UserGroupMember) o;
    return groupId.equals(that.groupId) && userId.equals(that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, userId);
  }

  /**
   * 用户组成员主键
   */
  public static class UserGroupMemberId {

    private String groupId;
    private String userId;
  }
}

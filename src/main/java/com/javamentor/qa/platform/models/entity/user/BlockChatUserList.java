package com.javamentor.qa.platform.models.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlockChatUserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User profile;

    @ManyToOne(fetch = FetchType.LAZY)
    private User blocked;

    private LocalDateTime persistDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockChatUserList that = (BlockChatUserList) o;
        return Objects.equals(id, that.id) && Objects.equals(profile, that.profile) && Objects.equals(blocked, that.blocked) && Objects.equals(persistDate, that.persistDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profile, blocked, persistDate);
    }
}

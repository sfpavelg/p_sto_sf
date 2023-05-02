package com.javamentor.qa.platform.models.entity;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_bookmark")
public class GroupBookmark {
    @Id
    @GeneratedValue(generator = "Group_Bookmark_id_seq")
    private Long id;
    @Column
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_bookmark_link",
            joinColumns = @JoinColumn(name = "group_bookmark_id"),
            inverseJoinColumns = @JoinColumn(name = "bookmark_id"))
    private Set<BookMarks> bookMarks;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
package org.top.springwithdbexample;

import jakarta.persistence.*;

import java.util.Date;

// в сущностях всегда используем обертки
@Entity
@Table(name="user_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // здесь будет происходить автогенерация
    private Integer id;

    // столбцы
    @Column(name="username_f", nullable = false, unique = true)
    private String username;

    @Column(name="registered_at_f", nullable = false)
    private Date registeredAt;

    @Column(name="discount_points_f")
    private Integer discountPoints;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Integer getDiscountPoints() {
        return discountPoints;
    }

    public void setDiscountPoints(Integer discountPoints) {
        this.discountPoints = discountPoints;
    }
}

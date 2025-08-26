package org.top.springwithdbexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="user_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username_f", nullable = false, unique = true)
    private String username;

    @Column(name="registered_at_f", nullable = false)
    private Date registeredAt;

    @Column(name="discount_points_f")
    private Integer discountPoints;

    // связь с сущностью (таблицей) заказов
    // 'mappedBy' - отображено чем (здесь надо указать название поля сущности, на которую ссылаемся)
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orders;

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

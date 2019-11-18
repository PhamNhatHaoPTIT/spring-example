package com.haopn.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPerson implements Serializable {
    private long id;
    private String abc;
    private String xyz;

    @Override
    public String toString() {
        return "NewPerson{" +
                "id=" + id +
                ", abc='" + abc + '\'' +
                ", xyz='" + xyz + '\'' +
                '}';
    }
}

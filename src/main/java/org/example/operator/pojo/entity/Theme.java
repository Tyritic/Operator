package org.example.operator.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theme implements Serializable{
        private Long tid;
        private String tname;
        private String turl;
        private Long mid;
}

package org.github.omnbmh.cobra.entity;

import lombok.Data;
import org.github.omnbmh.cobra.base.BaseEntity;

import java.util.Date;

@Data
public class Role extends BaseEntity {

    private String name;

    private String nameZh;
}
package org.github.omnbmh.cobra.base;

import lombok.Data;
import org.github.omnbmh.cobra.commons.tools.IdGenTools;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    protected Long id;

    protected String no;

    protected Date createAt;

    protected String creator;

    protected Date modifyAt;

    protected String modifier;

    protected String remark;

    protected Boolean delFlag;
    protected Boolean isDel;

    public void preInsert() {
        this.no = IdGenTools.getId();
        this.createAt = new Date();
        this.creator = "system";
        this.delFlag = Boolean.TRUE;
        this.isDel = Boolean.TRUE;
    }
}

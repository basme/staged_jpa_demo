package com.netcracker.training.hibernate.demo.model.relations;

import com.netcracker.training.hibernate.demo.model.bean.PageBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(of = {"pageId", "number", "isTechnical"})
@ToString(of = {"pageId", "number", "isTechnical"})
@Entity
@NamedNativeQuery(name = "page.get", query = "select * from page", resultSetMapping = "mapping.page")
@SqlResultSetMapping(name = "mapping.page",
        entities = @EntityResult(entityClass = Page.class, fields = {
                @FieldResult(name = "pageId", column = "pageid"),
                @FieldResult(name = "number", column = "number"),
                @FieldResult(name = "isTechnical", column = "istechnical"),
                @FieldResult(name = "book", column = "bookid")
        }

        ),
        classes = {
                @ConstructorResult(targetClass = PageBean.class, columns = {
                        @ColumnResult(name = "number", type = Integer.class),
                        @ColumnResult(name = "istechnical", type = Boolean.class)
                }
                )
        },
        columns = {
                @ColumnResult(name = "bookid", type = BigInteger.class)

        }

)
public class Page {

    @Id
    @GeneratedValue
    private BigInteger pageId;

    private Integer number;
    private boolean isTechnical;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

}

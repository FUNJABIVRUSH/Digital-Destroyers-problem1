package com.destroyers.spaceallocation.entities;

import com.destroyers.spaceallocation.model.space.SpaceQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space")
@Entity
@SqlResultSetMapping(name= "SpaceQuery", classes = {
        @ConstructorResult(targetClass = SpaceQueryResponse.class,
                columns = {
                        @ColumnResult(name="buildingName",type = String.class),
                        @ColumnResult(name="floorId",type = Long.class),
                        @ColumnResult(name="floorName",type = String.class),
                        @ColumnResult(name="zoneId",type = Long.class),
                        @ColumnResult(name="zoneName",type = String.class),
                        @ColumnResult(name="seatId",type = Long.class),
                        @ColumnResult(name="seatNumber",type = String.class),
                        @ColumnResult(name="seatType",type = String.class),
                        @ColumnResult(name="isReserved",type = Boolean.class),
                }
        )
})
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "range_id", referencedColumnName = "id")
    private SeatRange range;

}

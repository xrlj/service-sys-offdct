package com.xrlj.servicesysoffdct.api.vo;

import com.xrlj.framework.vo.VBaseResp;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VPdfKeyCoordinateResp extends VBaseResp {

    private static final long serialVersionUID = 140670697872669908L;

    private List<PositionListDTO> positionList;
    private String keyword;

    @NoArgsConstructor
    @Data
    public static class PositionListDTO {
        private Integer pageIndex;
        private List<CoordinateListDTO> coordinateList;

        @NoArgsConstructor
        @Data
        public static class CoordinateListDTO {
            private Float posx;
            private Float posy;
        }
    }
}

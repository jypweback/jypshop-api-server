package com.jypshop.dto;

import com.jypshop.domain.Delivery;
import com.jypshop.domain.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class DeliveryResponse {

    private Long deliveryId;

    private AddressResponse addressResponse;

    private DeliveryStatus deliveryStatus;

    @Builder
    public DeliveryResponse(Long deliveryId, AddressResponse addressResponse, DeliveryStatus deliveryStatus) {
        this.deliveryId = deliveryId;
        this.addressResponse = addressResponse;
        this.deliveryStatus = deliveryStatus;
    }
}

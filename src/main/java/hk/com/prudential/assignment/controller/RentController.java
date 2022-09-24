package hk.com.prudential.assignment.controller;

import hk.com.prudential.assignment.common.DataResult;
import hk.com.prudential.assignment.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping("/rent/car")
    public DataResult<Integer> rentCar(Integer carId, Integer customerId, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {

        if (Objects.isNull(carId) || Objects.isNull(customerId) || Objects.isNull(startTime) || Objects.isNull(endTime)) {
            return DataResult.error("missing parameter");
        }

        if (startTime.after(endTime)) {
            return DataResult.error("startTime should be before endTime.");
        }

        return rentService.rentCar(customerId, carId, startTime, endTime);

    }

    @RequestMapping("/return/car")
    public DataResult<Integer> returnCar(Integer customerId, Integer orderId) {

        if (Objects.isNull(orderId) || Objects.isNull(customerId)) {
            return DataResult.error("missing parameter");
        }

        rentService.returnCar(customerId, orderId);

        return DataResult.success(null);

    }


}

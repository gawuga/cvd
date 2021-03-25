package com.example.demo.controller;

import com.example.demo.entiy.HistoryQueryCondition;
import com.example.demo.entiy.data.*;
import com.example.demo.service.HistoryDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryDataController {
    @Resource
    private HistoryDataService historyDataService;

    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/logo")
    public List<Logo> getLogoData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLogoData(historyQueryCondition);
    }

    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/led")
    public List<Led> getLedData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLedData(historyQueryCondition);
    }


    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/label")
    public List<Label> getLabelData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLabelData(historyQueryCondition);
    }


    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/code")
    public List<Code> getCodeData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getCodeData(historyQueryCondition);
    }



    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/aoi")
    public List<Aoi> getAoiData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getAoiData(historyQueryCondition);
    }



    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/socket")
    public List<Socket> getSocketData(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getSocketData(historyQueryCondition);
    }


    /*******************按月得到的数据**--------------------------------------------------*/
    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */



    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/codeByMonth")
    public List<Code> getCodeDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getCodeDataByMonth(historyQueryCondition);
    }

    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/socketByMonth")
    public List<Socket> getSocketDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getSocketDataByMonth(historyQueryCondition);
    }






    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/aoiByMonth")
    public List<Aoi> getAoiDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getAoiDataByMonth(historyQueryCondition);
    }



    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/labelByMonth")
    public List<Label> getLabelDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLabelDataByMonth(historyQueryCondition);
    }



    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/ledByMonth")
    public List<Led> getLedDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLedDataByMonth(historyQueryCondition);
    }


    /**
     * 根据条件获取设备的历史数据
     * @param historyQueryCondition
     * @return
     */
    @GetMapping("/logoByMonth")
    public List<Logo> getLogoDataByMonth(HistoryQueryCondition historyQueryCondition) {

        System.out.println(historyQueryCondition.getTime());
        System.out.println(historyQueryCondition.getCurrentPage());

        return historyDataService.getLogoDataByMonth(historyQueryCondition);
    }
}

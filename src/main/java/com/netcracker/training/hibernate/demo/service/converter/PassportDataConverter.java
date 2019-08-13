package com.netcracker.training.hibernate.demo.service.converter;

import com.netcracker.training.hibernate.demo.model.embeddables.PassportData;
import com.netcracker.training.hibernate.demo.service.PassportDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Converter
@Component
public class PassportDataConverter implements AttributeConverter<PassportData, String> {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private final String DELIMITER = ";";

    private static PassportDeskService deskService;

    @Autowired
    public void setSaltGenerator(PassportDeskService deskService) {
        PassportDataConverter.deskService = deskService;
    }


    @Override
    public PassportData convertToEntityAttribute(String raw) {
        if(StringUtils.isEmpty(raw)) return null;
        final String[] parts = raw.split(DELIMITER);
        try {
            return new PassportData(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), DATE_FORMAT.parse(parts[2]),
                    deskService.getPassportDeskNameByCode(Integer.parseInt(parts[3])));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToDatabaseColumn(PassportData passportData) {
        if(passportData == null) return null;
        return String.format("%s;%s;%s;%s", passportData.getSer(), passportData.getNo(), DATE_FORMAT.format(passportData.getIssued()),
                deskService.getPassportDeskCodeByName(passportData.getIssuedBy()));
    }

}

package web.service;

import domain.Province;

import java.util.List;

public interface ProvinceService {
    public List<Province> findAllProvince();

    public String finaAllJson();
}

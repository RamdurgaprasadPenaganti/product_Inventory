package com.project.marketing.service;

import com.project.marketing.entity.PDEntity;
import com.project.marketing.repository.PDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PDService {

    @Autowired
    private PDRepo pdRepo;

    public String addFile(MultipartFile file) {

        try {
            File file1 = new File("C:\\Users\\surin\\Downloads\\sample1.csv");

            try (OutputStream os = new FileOutputStream(file1)) {
                os.write(file.getBytes());
            }

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\surin\\Downloads\\sample1.csv"));
            String nextline;

            while ((nextline = br.readLine()) != null) {

                pdRepo.save(addToDB(nextline));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "successfull";
    }

    private PDEntity addToDB(String arr1) throws ParseException {

        PDEntity pd = new PDEntity();

        String[] arr = arr1.split(",");

        pd.setCode(arr[0]);
        pd.setName(arr[1]);
        pd.setBatch(arr[2]);
        pd.setStock(Integer.parseInt(arr[3]));
        pd.setDeal(Integer.parseInt(arr[4]));
        pd.setFree(Integer.parseInt(arr[5]));
        pd.setMrp(Double.parseDouble(arr[6]));
        pd.setRate(Double.parseDouble(arr[7]));

        String str = "";
        String date = arr[8];
        String[] a = date.split("/");

        if (a.length == 3 && a[0] != null) {
            str = a[2] + "-" + a[1] + "-" + a[0];
            Date date1 = Date.valueOf(str);
            pd.setExp(date1);
        }

        pd.setCompany(arr[9]);
        if (arr.length == 11) {
            pd.setSupplier(arr[10]);
        } else {
            pd.setSupplier("  ");
        }

        return pd;
    }

    public Page<PDEntity> getStock(String name, Pageable page) {

        return pdRepo.findAllBySupplier(name, page);
    }

    public Page<PDEntity> getAllDetails(Pageable page) {

        return pdRepo.findAll(page);
    }

    public List<PDEntity> getStockByNotExp(String name, Pageable page) {

        Page<PDEntity> list1 = getStock(name, page);
        List<PDEntity> list2 = new ArrayList<>();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (PDEntity product : list1) {

            if (product.getExp() != null) {


                if (date.before(product.getExp())) {
                    list2.add(product);
                }
            }
        }
        return list2;
    }

    public List<PDEntity> getStockByProductName(String name, Pageable page) {
        Page<PDEntity> list1 = pdRepo.findAllByName(name, page);
        List<PDEntity> list2 = new ArrayList<>();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (PDEntity product : list1) {

            if (product.getStock() > 0) {
                list2.add(product);
            }
        }
        return list2;
    }
}



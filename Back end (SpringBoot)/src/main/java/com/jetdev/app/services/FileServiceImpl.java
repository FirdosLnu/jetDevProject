package com.jetdev.app.services;

import com.jetdev.app.model.Employee;
import com.jetdev.app.model.File;
import com.jetdev.app.model.Response;
import com.jetdev.app.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;
    @Override
    public String processFile(MultipartFile file) {
        try {
            File fileObject = new File();
            if(file.getOriginalFilename() != null){
                File savedFile = fileRepository.findByName(file.getOriginalFilename());
                if(savedFile != null){
                    fileObject = savedFile;
                }
            }

            fileObject.setName(file.getOriginalFilename());
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                /*if (workbook.getNumberOfSheets() < 0) {
                    numberOfSheet = workbook.getNumberOfSheets();
                }*/
            //for (int i = 0; i < numberOfSheet; i++) {
            // Getting the Sheet at index i
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("=> " + sheet.getSheetName());
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            // 1. You can obtain a rowIterator and columnIterator and iterate over them
            System.out.println("Iterating over Rows and Columns using Iterator");
            Iterator<Row> rowIterator = sheet.rowIterator();
            List<Employee> employeeList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int rowNumber = row.getRowNum();
                if (rowNumber == 0) {
                    continue;
                }
                Employee employee = new Employee();
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);

                employee.setFile(fileObject);
                employee.setName(cell1.getStringCellValue());
                employee.setEmail(cell2.getStringCellValue());
                employee.setSalary(cell3.getNumericCellValue());
                employeeList.add(employee);
            }
            fileObject.setEmployeeList(employeeList);
            fileObject.setDateModified(new Date());
            fileRepository.save(fileObject);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response getFile(String fileName, String auth){
        Response response = new Response();
        String[] credentials = auth.split(" ");
        File file = fileRepository.findByName(fileName);
        file.setDateModified(new Date());
        log.info("User who review file {}", credentials[0]);
        fileRepository.save(file);
        response.setStatus(200L);
        response.setData(Arrays.asList(file));
        return response;
    }

    @Override
    public Response getAllFile() {
        Response response = new Response();
        List<File> fileList = fileRepository.findAll();
        response.setData(fileList);
        response.setStatus(200L);
        return response;
    }

    public Response deleteFile(String fileName){
        Response response = new Response();
        try {
            File file = fileRepository.findByName(fileName);
            fileRepository.delete(file);
            response.setMsg("Successfully Deleted File");
            response.setStatus(200l);
        } catch (Exception exception){
            response.setMsg("Error deleting file");
            response.setStatus(500l);
        }
        return response;
    }
}

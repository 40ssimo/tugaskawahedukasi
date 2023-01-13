package id.kawahedukasi.service;

import id.kawahedukasi.model.Item;
import id.kawahedukasi.model.dto.UploadItemRequest;
import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class UploadService {
    public Response upload(UploadItemRequest request) throws IOException {
        List<Item> itemList = new ArrayList<>();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.file);
        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        sheet.removeRow(sheet.getRow(0));

        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Item item = new Item();

            Row row = rowIterator.next();
            item.name = row.getCell(0).getStringCellValue();
            item.count = Double.valueOf(row.getCell(1).getNumericCellValue()).intValue();
            item.price = Double.valueOf(row.getCell(2).getNumericCellValue()).intValue();
            item.type = row.getCell(3).getStringCellValue();
            item.description = row.getCell(4).getStringCellValue();
            itemList.add(item);
        }

        persistListItem(itemList);
        return Response.ok().build();

    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public void persistListItem(List<Item> itemList){
        Item.persist(itemList);
    }

}

package id.kawahedukasi.service;

import javax.enterprise.context.ApplicationScoped;

import id.kawahedukasi.model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ReportService {
    public Response exportJasper() throws JRException {
        File file = new File("src/main/resources/item2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getAllItem());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter(), dataSource);
        byte[] jasperResult = JasperExportManager.exportReportToPdf(jasperPrint);
        return Response.ok().type("application/pdf").entity(jasperResult).build();
    }

    public List<Item> getAllItem(){
        List<Item> list = Item.listAll();
        return list;
    }

    public Map<String, Object> jasperParameter(){
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("createdBy", "Ichsan");
        return parameter;
    }

}

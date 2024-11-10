package com.example.microservice.service;


import com.example.microservice.DTO.Domain;
import com.example.microservice.entity.Cour;
import com.example.microservice.entity.Niveau;
import com.example.microservice.proxies.DomainClient;
import com.example.microservice.repository.ICourRepository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourServiceImpl implements ICourService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    ICourRepository iCourRepository;
   /* @Autowired
    UserRepo iUserRepository;
    @Autowired
    IRessourceRepository iRessourceRepository;
    @Autowired
    UserRepo userRepo;
@Autowired
    IDomaineRepo iDomaineRepo;*/
   @Autowired
   private DomainClient domainClient;

    public List<Domain>Listededomaine(){
        return domainClient.Listededomaine();
    }
    public Domain addDomaine(@RequestBody Domain d){
        return domainClient.addDomaine(d);
    }

    @Override
    public Cour ajouterCour(Cour c ) {

        Date date = new Date();
        c.setDate(date);
        /*Domaine d=iDomaineRepo.findDomaineByNom(c.getNomDomaine());
        c.setDomaine(d);
        List<Ressource> ressourceList = new ArrayList<>();
        ressourceList = c.getRessourceList();
        iRessourceRepository.saveAll(ressourceList);*/
        return iCourRepository.save(c);
    }

    @Override
    public Cour modifierCour(Cour c, String idc) {
        log.info("***************************************************" + c.getNomCour());
        Cour co = new Cour();
        co = iCourRepository.findById(idc).get();
        co.setNomCour(c.getNomCour());
        co.setDescription(c.getDescription());
        co.setPrix(c.getPrix());

        return iCourRepository.save(co);


    }

    @Override
    public void supprimerCour(String id) {
        Cour c = iCourRepository.findById(id).get();
        iCourRepository.delete(c);
    }

    @Override
    public List<Cour> getCour() {

        return iCourRepository.findAll();
    }

    @Override
    public List<Cour> findAllByOrderByDateDesc() {
        for (Cour c : iCourRepository.findAllByOrderByDateDesc()) {
            log.info("le nom est \n" + c.getNomCour());
        }
        return iCourRepository.findAllByOrderByDateDesc();
    }

    @Override
    public List<Cour> findAllByNomCour(String nom) {
        return iCourRepository.findAllByNomCour(nom);
    }

    private String generateNewFileName(String originalFileName) {
        // You can customize this method to generate a unique file name.
        // For example, appending a timestamp or using a UUID.
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName;
    }

    @Override
    public String storeFile(MultipartFile file, String idCour) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFileName = generateNewFileName(originalFileName);

        Path uploadPath = Paths.get(uploadDir);

        try {
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);

            Cour cour = iCourRepository.findById(idCour).get();
            cour.setPhoto(newFileName);
            iCourRepository.save(cour);

            return newFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + newFileName, e);
        }
    }






    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }

    @Override
    public Cour getCCourByid(String id) {
        return iCourRepository.findById(id).get();
    }

  /*  @Override
    public Cour affecterRessourcesACour(Ressource r, String idc) {
        Cour co = iCourRepository.findById(idc).get();

            if (iRessourceRepository.existsById(r.getIdRessource())) {
                co.getRessourceList().add(r);
                log.info("yessss");
            }
           else{
             co.getRessourceList().add(r);
            iRessourceRepository.save(r);
                log.info("yessss shway");

            }

        return iCourRepository.save(co);
    }

    @Override
    public String storeFileRessource(MultipartFile file, String idRessource) {
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String newFileName = generateNewFileName(originalFileName);

            Path uploadPath = Paths.get(uploadDir);

            try {
                if (Files.notExists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(newFileName);
                Files.copy(file.getInputStream(), filePath);

                Ressource ressource = iRessourceRepository.findById(idRessource).get();
                ressource.setPhoto(newFileName);
                iRessourceRepository.save(ressource);

                return newFileName;
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file: " + newFileName, e);
            }
        }
*/
    @Override
    public List<Cour> findCoursByDateGreaterThan() {
        LocalDate dateActuelleMoins20Jours = LocalDate.now().minusDays(3);

        Date dateMoins20Jours = Date.from(dateActuelleMoins20Jours.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return iCourRepository.findByDateGreaterThan(dateMoins20Jours);
    }

    @Override
    public List<Cour> filterByNiveau(Niveau niveau) {
        return iCourRepository.findCoursByNiveau(niveau);
    }

    @Override
    public List<Cour> rechercheCour(String recherche) {
        return iCourRepository.findByNomCourIgnoreCaseOrDescriptionIgnoreCase(recherche,recherche);

    }

   /* @Override
    public List<Cour> getCourByDomaine(String idDomaine) {
        return iCourRepository.findCoursByDomaine_Id(idDomaine);
    }*/


    public static void addTableHeader(PdfPTable table) {
        // Ajouter les en-têtes de colonnes
        table.addCell("Nom");
        table.addCell("Date");
        table.addCell("Montant");
    }

    public static void addCustomRows(PdfPTable table) {
        // Exemple de données de clients

        // Ajoutez autant de lignes de clients que nécessaire
    }

    public static void addRow(PdfPTable table, String name, Date date, Long amount) {
        // Ajouter une ligne avec les données du client
        table.addCell(name);
        table.addCell(date.toString()); // Vous pouvez formater la date selon vos préférences
        table.addCell(String.valueOf(amount));
    }
    public static BaseFont getBaseFont() throws DocumentException, IOException {
        // Utilisation d'une police de base intégrée
        return BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
    }

}

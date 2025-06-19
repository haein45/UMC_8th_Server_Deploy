package umc.spring.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.config.AmazonConfig;
import umc.spring.repository.UuidRepository;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;
    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public String generateReviewKeyName(Uuid uuid) {
        return amazonConfig.getReviewPath() + "/" + uuid.getUuid();

    }

    public String uploadFile(MultipartFile file) {
        String keyName = "profile/" + java.util.UUID.randomUUID();
        return uploadFile(keyName, file);
    }

    public void deleteFile(String keyName) {
        if (amazonS3.doesObjectExist(amazonConfig.getBucket(), keyName)) {
            amazonS3.deleteObject(amazonConfig.getBucket(), keyName);
        }
    }

    public String extractKeyFromImageUrl(String imageUrl) {
        String bucketUrl = "https://" + amazonConfig.getBucket() + ".s3." + amazonConfig.getRegion() + ".amazonaws.com/";
        return imageUrl.replace(bucketUrl, "");
    }




}


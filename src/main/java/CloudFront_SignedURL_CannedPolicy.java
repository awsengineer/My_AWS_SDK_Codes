/**
 * Created by mssalehi on 6/17/17.
 */

import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.util.IOUtils;
import org.jets3t.service.CloudFrontService;
import java.io.FileInputStream;
import java.security.Security;

public class CloudFront_SignedURL_CannedPolicy {
    public static void main(String[] args) {
        try {
            // CloudFront signing private key path.  Already Reformat to DER:
            String privateKeyFilePath = "/Users/mssalehi/keys/pk-APKAIMN2TXDRB53WEPFQ.der";
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] derPrivateKey = IOUtils.toByteArray(new FileInputStream(privateKeyFilePath));

            String distributionDomain = "d1qkkjuhp2kr5s.cloudfront.net";
            String s3ObjectKey = "aws.png";
            String policyResourcePath = "https://" + distributionDomain + "/" + s3ObjectKey;

            String keyPairId = "APKAIMN2TXDRB53WEPFQ"; // same as Access Key ID (when you create the CloudFront keys)
            java.util.Date url_expires_On = ServiceUtils.parseIso8601Date("2017-10-14T22:20:00.000Z");

            String signedUrlCanned =  CloudFrontService.signUrlCanned(
                    policyResourcePath,
                    keyPairId,
                    derPrivateKey,
                    url_expires_On
            );
            System.out.println("Here is the signed URL for "+policyResourcePath+" using a Canned Policy:");
            System.out.println(signedUrlCanned);
        }
        catch (java.io.IOException e){
            System.out.println(e.toString());
        }
        catch (org.jets3t.service.CloudFrontServiceException e){
            System.out.println(e.toString());
        }
    }
}
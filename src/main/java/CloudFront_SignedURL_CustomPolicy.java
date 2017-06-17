/**
 * Created by mssalehi on 6/17/17.
 */

import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.util.IOUtils;
import org.jets3t.service.CloudFrontService;

import java.io.FileInputStream;
import java.security.Security;

public class CloudFront_SignedURL_CustomPolicy {

        public static void main(String[] args) {
            try {
                String privateKeyFilePath = "/Users/mssalehi/keys/pk-APKAIMN2TXDRB53WEPFQ.der";

                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                String distributionDomain = "d1qkkjuhp2kr5s.cloudfront.net";
                String s3ObjectKey = "a.jpg";
                String policyResourcePath = "https://" + distributionDomain + "/" + s3ObjectKey;

                byte[] derPrivateKey = IOUtils.toByteArray(new FileInputStream(privateKeyFilePath));
                System.out.println(policyResourcePath);
                //String keyPairId = "AKIAIG66DBN5GJR5PGZA"; APKAIMN2TXDRB53WEPFQ
                String keyPairId = "APKAIMN2TXDRB53WEPFQ";
                java.util.Date expiresOn = ServiceUtils.parseIso8601Date("2017-02-14T22:20:00.000Z");
                System.out.println(expiresOn);

                String signedUrlCanned =  CloudFrontService.signUrlCanned(
                        //"https://" + distributionDomain + "/" + s3ObjectKey,
                        "https://d1qkkjuhp2kr5s.cloudfront.net/a.jpg",
                        //policyResourcePath,
                        keyPairId,
                        derPrivateKey,
                        expiresOn
                );
                System.out.println("signedUrlCanned:");
                System.out.println(signedUrlCanned);
                System.out.println("End of Canned policy.");
            /*
            // Build a policy document to define custom restrictions for a signed URL.
            String policy = CloudFrontService.buildPolicyForSignedUrl(
// Resource path (optional, can include '*' and '?' wildcards)
                    policyResourcePath,
// DateLessThan
                    ServiceUtils.parseIso8601Date("2017-11-14T22:20:00.000Z"),

                    "52.52.10.0/24",
                    // DateGreaterThan (optional)
                    ServiceUtils.parseIso8601Date("2017-10-16T06:31:56.000Z")
            );

            // Generate a signed URL using a custom policy document.
            String signedUrl = CloudFrontService.signUrl(
                    "https://" + distributionDomain + "/" + s3ObjectKey,
                    keyPairId,
                    derPrivateKey,
                    policy
            );
            System.out.println("policy:");
            System.out.println(policy);
            System.out.println("signedUrl:");
            System.out.println(signedUrl);  */
            }
            catch (java.io.IOException e){
                System.out.println(e.toString());
            }
            catch (org.jets3t.service.CloudFrontServiceException e){
                System.out.println(e.toString());
            }
        }
    }



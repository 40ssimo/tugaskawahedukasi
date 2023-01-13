package id.kawahedukasi.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NotificationService {
    @Inject
    Mailer mailer;
    public void sendEmail() {
        mailer.send(Mail.withText("ichsansanpratama@gmail.com", "Coba Kirim Email", "Halo tes coba kirim email"));
    }
}

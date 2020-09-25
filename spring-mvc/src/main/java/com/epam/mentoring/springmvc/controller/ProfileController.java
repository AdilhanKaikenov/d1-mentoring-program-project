package com.epam.mentoring.springmvc.controller;

import com.epam.mentoring.springmvc.model.UserModel;
import com.epam.mentoring.springmvc.service.UserService;
import com.epam.mentoring.springmvc.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * @author Kaikenov Adilhan
**/
@Controller
public class ProfileController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private static final String USERNAME_PATH_VARIBLE = "{username}";
    private static final String PROFILE_URL = "/profile/" + USERNAME_PATH_VARIBLE;
    private static final String REDIRECT_PROFILE_URL = "redirect:" + PROFILE_URL;
    private static final String PROFILE_USERNAME_UPLOAD_PHOTO_URL = "/profile/{username}/uploadPhoto";
    private static final String GET_PROFILE_PHOTO_URL = "/profile/photo/{photo:.+}";

    private static final String PROFILE_VIEW_NAME = "profile";
    private static final String PAGE_MODEL = "userModel";

    private static final String USER_PROFILE_PHOTO_LOCATION = "D:\\photos";

    @Autowired
    private UserService userService;

    @GetMapping(PROFILE_URL)
    public ModelAndView renderPage(@PathVariable final String username) {
        log.info("Show profile page for {}", username);

        try {
            final UserVO userVO = userService.findVoByUsername(username);
            final UserModel userModel = mapUserVoToModel(userVO);

            return new ModelAndView(PROFILE_VIEW_NAME, PAGE_MODEL, userModel);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("InternalServerErrorException: Failed to render profile page for " + username, e);
        }
    }

    @PostMapping(PROFILE_USERNAME_UPLOAD_PHOTO_URL)
    public ModelAndView uploadPhoto(@PathVariable final String username, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        final File userPhotoDir = new File(USER_PROFILE_PHOTO_LOCATION);
        checkProfileStorage(userPhotoDir);

        if (!file.isEmpty()) {
            proceedUploadPhoto(username, file, redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute(NOTIFICATION, "upload.photo.info.notification.message");
        }

        return new ModelAndView(REDIRECT_PROFILE_URL.replace(USERNAME_PATH_VARIBLE, username));
    }

    // Fixed Truncation of PathVariable {photo:.+}
    @GetMapping(value = GET_PROFILE_PHOTO_URL, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable final String photo) {
        final String photoPath = USER_PROFILE_PHOTO_LOCATION + File.separator + photo;
        final File imageFile = new File(photoPath);

        final byte[] image = readFileToByteArray(imageFile);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    private void proceedUploadPhoto(String username, MultipartFile file, RedirectAttributes redirectAttributes) {
        final String filename = file.getOriginalFilename();
        final String photoPath = USER_PROFILE_PHOTO_LOCATION + File.separator + filename;
        try (final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(photoPath))) {
            final byte[] fileBytes = file.getBytes();

            outputStream.write(fileBytes);
            outputStream.flush();

            log.info("Photo with {} name has been successfully uploaded to the {} directory.", filename, USER_PROFILE_PHOTO_LOCATION);

            userService.updateProfilePhoto(username, filename);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(NOTIFICATION, "upload.photo.error.notification.message");
        }
    }

    private byte[] readFileToByteArray(File file) {
        byte[] byteArray = new byte[(int) file.length()];
        try (final FileInputStream fileInputStream = new FileInputStream(file)) {

            fileInputStream.read(byteArray);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return byteArray;
    }

    private void checkProfileStorage(final File userPhotoDir) {
        if (!userPhotoDir.exists() && userPhotoDir.isDirectory()) {
            if (userPhotoDir.mkdir()) {
                log.debug("User profile storage directory {} created", USER_PROFILE_PHOTO_LOCATION);
            }
        }
    }

}

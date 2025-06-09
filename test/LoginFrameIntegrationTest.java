//import basarozkasli.view.swing.LoginFrame;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class LoginFrameTest {
//    private LoginFrame frame;
//    private DummyLoginController dummyController;
//
//    static class DummyLoginController extends basarozkasli.controller.LoginController {
//        String lastUsername;
//        String lastPassword;
//        boolean handleLoginCalled = false;
//
//        public DummyLoginController() {
//            super(null, null, null);
//        }
//
//        @Override
//        public void handleLogin(String username, String password) {
//            handleLoginCalled = true;
//            lastUsername = username;
//            lastPassword = password;
//        }
//    }
//
//    @BeforeEach
//    void setUp() {
//        frame = new LoginFrame();
//        dummyController = new DummyLoginController();
//        frame.setController(dummyController);
//    }
//
//    @Test
//    void testLoginButtonTriggersController() {
//        frame.UserNameText.setText("testuser");
//        frame.PasswordPasswordField.setText("testpass");
//
//        // Butona tıklama simülasyonu
//        frame.LoginButton.doClick();
//
//        // Controller'ın handleLogin metodunun çağrıldığını ve doğru parametrelerle çağrıldığını test et
//        assertTrue(dummyController.handleLoginCalled, "handleLogin() çağrılmalı");
//        assertEquals("testuser", dummyController.lastUsername);
//        assertEquals("testpass", dummyController.lastPassword);
//    }
//
//    @Test
//    void testShowErrorDisplaysDialog() {
//        // Hata mesajı için dialogu göstermek zor olduğu için, burada kodun çalışabilirliğini test edelim
//        assertDoesNotThrow(() -> frame.showError("Hatalı giriş!"));
//    }
//}

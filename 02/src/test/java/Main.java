import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.service.QuestionsService;

public class Main {

    @Value("${min.correct}")
    public static Integer minCorrectAnswers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ru.otus.spring.Main.class);
        QuestionsService service = context.getBean(QuestionsService.class);

        UserTestImpl user = new UserTestImpl(7);
        var name = user.askMe("Enter your first name: ");
        name += " ";
        name += user.askMe("Enter your last name: ");

        int countCorrectAnswers = service.run(user);

        System.out.println("---\n" + name + ", your result: " + countCorrectAnswers + " correct answer(s)");

        context.close();
    }
}

package isi.deso.tp_spring.validation;

import isi.deso.tp_spring.service.ContextoPagoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Validate that the id value isn't taken yet.
 */
@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ContextoPagoPagoUnique.ContextoPagoPagoUniqueValidator.class
)
public @interface ContextoPagoPagoUnique {

    String message() default "{Exists.contextoPago.pago}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ContextoPagoPagoUniqueValidator implements ConstraintValidator<ContextoPagoPagoUnique, Integer> {

        private final ContextoPagoService contextoPagoService;
        private final HttpServletRequest request;

        public ContextoPagoPagoUniqueValidator(final ContextoPagoService contextoPagoService,
                final HttpServletRequest request) {
            this.contextoPagoService = contextoPagoService;
            this.request = request;
        }

        @Override
        public boolean isValid(final Integer value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked")
            final Map<String, String> pathVariables
                    = ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(contextoPagoService.get(Integer.parseInt(currentId)).getPago())) {
                // value hasn't changed
                return true;
            }
            return !contextoPagoService.pagoExists(value);
        }

    }

}

package isi.deso.tp_spring.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import isi.deso.tp_spring.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the cuit value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ClienteCuitUnique.ClienteCuitUniqueValidator.class
)
public @interface ClienteCuitUnique {

    String message() default "{Exists.cliente.cuit}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ClienteCuitUniqueValidator implements ConstraintValidator<ClienteCuitUnique, String> {

        private final ClienteService clienteService;
        private final HttpServletRequest request;

        public ClienteCuitUniqueValidator(final ClienteService clienteService,
                final HttpServletRequest request) {
            this.clienteService = clienteService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(clienteService.get(Integer.parseInt(currentId)).getCuit())) {
                // value hasn't changed
                return true;
            }
            return !clienteService.cuitExists(value);
        }

    }

}
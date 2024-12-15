package isi.deso.tp_spring.validation;

import isi.deso.tp_spring.service.PedidoService;
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


@Target({FIELD, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = PedidoContextoPagoUnique.PedidoContextoPagoUniqueValidator.class
)
public @interface PedidoContextoPagoUnique {

    String message() default "{Exists.pedido.contextoPago}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PedidoContextoPagoUniqueValidator implements ConstraintValidator<PedidoContextoPagoUnique, Integer> {

        private final PedidoService pedidoService;
        private final HttpServletRequest request;

        public PedidoContextoPagoUniqueValidator(final PedidoService pedidoService,
                final HttpServletRequest request) {
            this.pedidoService = pedidoService;
            this.request = request;
        }

        @Override
        public boolean isValid(final Integer value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            final Map<String, String> pathVariables
                    = ((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(pedidoService.get(Integer.parseInt(currentId)).getContextoPago())) {
                return true;
            }
            return !pedidoService.contextoPagoExists(value);
        }

    }

}

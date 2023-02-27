package com.example.geradorXLSX.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.geradorXLSX.exceptionhandler.Problem;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;


@Configuration
public class SpringDocConfig {
    private @Value("${documentacao.api.versao}") String versao;
    private @Value("${documentacao.api.titulo}") String titulo;
    private @Value("${documentacao.api.descricao}") String descricao;
    private @Value("${documentacao.api.externo.descricao}") String externoDescricao;
    private @Value("${documentacao.api.externo.url}") String externoUrl;

    private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
    private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(titulo)
                        .version(versao)
                        .description(descricao)
                ).components(new Components()
                        .schemas(gerarSchemas())
                        .responses(gerarResponses())
                );
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> openApi.getPaths()
                .values()
                .forEach(pathItem -> pathItem.readOperationsMap()
                        .forEach((httpMethod, operation) -> {
                            ApiResponses responses = operation.getResponses();
                            switch (httpMethod) {
                                case GET:
                                    responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                    break;
                                case PUT, POST:
                                    responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                                    responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                    break;
                                case DELETE:
                                    responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                    break;
                                default:
                                    responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                                    break;
                            }
                        })
                );
    }

    public Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();
        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);
        schemaMap.putAll(problemSchema);
        return schemaMap;
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("Problema")));

        Content contentInternalServerErrorResponse = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().example(getContentInternalServerErrorResponse()));

        apiResponseMap.put(BAD_REQUEST_RESPONSE, new ApiResponse()
                .description("Requisição inválida")
                .content(content));

        apiResponseMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse()
                .description("Erro interno no servidor")
                .content(contentInternalServerErrorResponse));

        return apiResponseMap;
    }

    private Object getContentInternalServerErrorResponse() {
        var objectInternalServerErrorResponse = new LinkedHashMap<String, Object>();
        objectInternalServerErrorResponse.put("status", 500);
        objectInternalServerErrorResponse.put("type", "/erro-de-sistema");
        objectInternalServerErrorResponse.put("detail", "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.");
        objectInternalServerErrorResponse.put("userMessage", "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.");
        objectInternalServerErrorResponse.put("timestamp", "2022-11-07 09:45:50");
        return objectInternalServerErrorResponse;
    }
}
# Schoolar Backend (Java Spring Boot)

Este repositório contém o código-fonte e a documentação relacionados ao backend da plataforma Schoolar, desenvolvido em Java Spring Boot.

## Pré-requisitos
- JDK 8 ou superior
- Maven
- Docker

## Instruções de Instalação e Execução
1. Clone este repositório: [https://github.com/Challenge2023/schoolar-backend](https://github.com/Challenge2023/schoolar-backend)
2. Navegue até o diretório clonado: cd schoolar-backend
3. Execute o docker compose através do comando: sudo docker-compose up
4. Executar o arquivo: SchoolarApiApplication


## Funcionalidades do Backend
O backend da plataforma Schoolar oferece um conjunto abrangente de funcionalidades para suportar as necessidades dos alunos e professores:

- **API RESTful**: O backend fornece uma API RESTful para comunicação e integração com o frontend da Schoolar, garantindo uma experiência fluida e consistente para os usuários.

- **Inteligência Artificial (IA)**: O sistema utiliza inteligência artificial para analisar o desempenho dos alunos, criar planos de estudo personalizados, auxiliar na criação de provas e na correção automatizada de respostas. A IA é o coração da personalização e eficiência da plataforma.

- **Banco de Dados**: Utiliza-se o MongoDB, um banco de dados NoSQL orientado a documentos, para armazenar e gerenciar informações de usuários, desempenho acadêmico, planos de estudo e outros dados relevantes. O banco de dados é otimizado para garantir um desempenho rápido e eficiente, mesmo com um grande número de usuários e dados.

- **Integração do ChatGPT**: O ChatGPT é integrado como um componente central para fornecer feedback personalizado e auxiliar na correção de provas e na preparação de aulas. Esta tecnologia avançada de IA possibilita uma experiência de aprendizado adaptativa e engajadora para os usuários.

- **APIs para Integração**: O backend utiliza APIs RESTful para facilitar a integração e a comunicação entre os diferentes componentes e serviços do sistema, garantindo uma arquitetura flexível e adaptável às necessidades dos usuários.

Estas funcionalidades combinadas formam a base sólida da Schoolar, possibilitando o alcance de seus objetivos e a entrega de uma experiência de aprendizado inovadora e personalizada.

# Documentação de Implantação

<details>
<summary>Documentação de Implantação MANUAL</summary>

## Índice

1. [Gerar build do projeto](#gerar-build-do-projeto)
2. [Criar um Grupo de Recursos](#criar-um-grupo-de-recursos)
3. [Criar um Registro de Contêiner do Azure (ACR)](#criar-um-registro-de-contêiner-do-azure-acr)
4. [Autenticar o Docker com o ACR](#autenticar-o-docker-com-o-acr)
5. [Construir a Imagem Docker Localmente](#construir-a-imagem-docker-localmente)
6. [Marcar e Empurrar a Imagem para o ACR](#marcar-e-empurrar-a-imagem-para-o-acr)
7. [Habilitar permissão de administrador](#habilitar-permissão-de-administrador)
8. [Verificar Senha do ACR](#verificar-senha-do-acr)
9. [Criar Contêiner da Aplicação Spring Boot](#criar-contêiner-da-aplicação-spring-boot)
10. [Verificar Estado da Implantação](#verificar-estado-da-implantação)

---

### Gerar build do projeto

Para gerar o build do projeto, execute o seguinte comando:

```bash
mvn clean install
```

---

### Criar um Grupo de Recursos

Para criar um novo grupo de recursos no Azure, use o comando:

```bash
az group create --name schoolargroup --location eastus
```

---

### Criar um Registro de Contêiner do Azure (ACR)

Execute o seguinte comando para criar um ACR:

```bash
az acr create --resource-group schoolargroup --name schoolaracr --sku Basic
```

---

### Autenticar o Docker com o ACR

Para autenticar o Docker com o ACR, execute o seguinte comando:

```bash
az acr login --name schoolaracr
```

---

### Construir a Imagem Docker Localmente

Execute o comando abaixo para construir a imagem Docker:

```bash
docker build -t schoolar-api .
```

---

### Marcar e Empurrar a Imagem para o ACR

Para marcar e empurrar a imagem para o ACR, use os seguintes comandos:

```bash
docker tag schoolar-api schoolaracr.azurecr.io/schoolar-api:v1
docker push schoolaracr.azurecr.io/schoolar-api:v1
```

---

### Habilitar permissão de administrador

Para habilitar a permissão de administrador no ACR, use o comando:

```bash
az acr update -n schoolaracr --admin-enabled true
```

---

### Verificar Senha do ACR

Para verificar a senha do ACR, use o comando:

```bash
az acr credential show --name schoolaracr
```

---

### Criar Contêiner da Aplicação Spring Boot

Para criar o contêiner da aplicação Spring Boot, execute o seguinte comando:

```bash
az container create --resource-group schoolargroup --name schoolar-api --image schoolaracr.azurecr.io/schoolar-api:v1 --cpu 1 --memory 1 --ports 8080 --ip-address public
```

---

### Verificar Estado da Implantação

Para verificar o estado da implantação, você pode usar os seguintes comandos:

```bash
az container show --resource-group schoolargroup --name schoolar-api --query "{FQDN:ipAddress.fqdn,IP:ipAddress.ip,ProvisioningState:provisioningState}" --out table
```

</details>

<details>
<summary>Documentação de Implantação AUTOMATIZADA - CI/CD</summary>

## Índice
1. [Introdução](#introdução)
2. [Descrição Detalhada do Pipeline](#descrição-detalhada-do-pipeline)
   1. [CI - Integração Contínua](#ci---integração-contínua)
   2. [CD - Entrega Contínua](#cd---entrega-contínua)
3. [Especificação Técnica](#especificação-técnica)
4. [Configuração do Ambiente](#configuração-do-ambiente)
5. [Segurança e Melhores Práticas](#segurança-e-melhores-práticas)
6. [Conclusão](#conclusão)

### Introdução
Este documento oferece uma visão detalhada do pipeline de CI/CD implementado para a Schoolar API, usando Azure DevOps. O objetivo é fornecer um entendimento claro de cada etapa e processo envolvido, garantindo eficiência e confiabilidade nas implantações.

### Descrição Detalhada do Pipeline

#### CI - Integração Contínua
1. **Trigger de Commit (Build Maven CI)**: 
   - O pipeline é iniciado automaticamente a cada commit na branch principal (`main`). 
   - **Nome da Build**: `Schoolar_API_CI_Build`.
2. **Execução de Testes com Maven (Test Execution)**:
   - `task: Maven@3`: A tarefa Maven é utilizada para compilar o código-fonte.
   - `inputs`: 
     - `mavenPomFile: 'pom.xml'`: Especifica o arquivo POM do Maven.
     - `goals: 'clean package'`: Limpa o build anterior e cria um novo pacote, incluindo a execução de testes.
   - **Resultado**: Gera um artefato (`schoolar-api.jar`) na pasta `target/`.

#### CD - Entrega Contínua
1. **Preparação para o Deploy (Pre-Deployment)**:
   - `task: AzureContainerApps@1`: Configura o deploy do aplicativo no Azure Container Apps.
   - `inputs`: 
     - `azureSubscription`: Especifica a subscrição Azure utilizada.
     - `acrName`, `acrUsername`, `acrPassword`: Detalhes do Azure Container Registry.
     - `containerAppName: 'schoolar-api'`: Nome do aplicativo de container.
     - `resourceGroup: 'schoolargroup'`: Grupo de recursos do Azure.
   - **Nome da Build**: `Schoolar_API_CD_Deployment`.
2. **Implantação no Azure Container Apps (Deployment Execution)**:
   - Utiliza a imagem Docker, especificada na build anterior, para realizar o deploy.
   - Configura o ambiente de produção, incluindo a definição da porta (`8080`) e localização geográfica (`eastus`).

### Especificação Técnica
- **Linguagem de Programação**: Java (Spring Boot).
- **Sistema de Build**: Maven.
- **Contêinerização**: Docker.
- **Ambiente de Produção**: Azure Container Apps.

### Configuração do Ambiente
- **Azure DevOps**: Usado para gerenciar e automatizar o pipeline.
- **Maven**: Gerencia dependências e processo de build.
- **Docker**: Criação de contêineres para a aplicação.
- **Azure Container Registry (ACR)**: Armazena imagens Docker.
- **Azure Container Apps**: Hospeda e gerencia a aplicação em contêineres.

### Segurança e Melhores Práticas
- **Gestão de Credenciais**: Uso de variáveis de grupo e segredos para proteger credenciais.
- **Monitoramento de Qualidade de Código**: Testes automatizados e revisões de código para manter altos padrões.

## Diagrama

[![]([https://mermaid.ink/img/pako:eNp1k8Fu2zAMhl-F8GkDmj2ADwMSO0ndNkXQZjvM6YGVmUSLJRmSPLSr-zDDDgMK9Lgn8IuNlp2sGTAfDFv6f34UST1FwhQUxdHWYrWDVbrWwM84z3T7KqSJITFKSQ8aYWJRix0sUOo7GI0-Nisrt1uyDUzeTWpZFrz1jTQk2fs-yiSopg8kao8NJPmKnCcHn7T07Q8rjQOCgiDTnpjf_mp_mrvemgTrbS3IOdNAmk9VhcJ4VKS9AWFUDzuRz7DcMWeaXxsvN1KghbDUMXr0IE-DfGw9bZCjzcliwZRZvqzvS_aFTDrXQRKDEztjSrQjrOSHr2iHSLMuEszzpaUKhyNA9wUpVaV5HGTzvl7o9jD-XltKjPZcRrLjqnINnOe84LytBzBCpnBLClIj9nRgnQdW1pWitpYPN4gCbpzcDLIsyC7yPgHQpkfCkQkddBBfhMR4ayO39SH_gi3qXnKlqYFLHgUpJOO6AO1LF2EwXwbSVf7W_pv7y4W7IX4RTB8q4-SxnEtjPQ7mq2Be5J_Jhl4d0VPnuRt9EaoSOem3g7EICZ_swITUqBuVQhbc_ut8KSsqOc8uY1HW7WvRT8wwTieR-gHR_8D-M0THnkZnkSKrUBZ8dZ66eOvI70jROor5s0C7X0dr_cw6rL25fdQiirm_dBbVVYGeUok88SqKN1g6Xq1QfzHm7z-fxRu76O9muKLPfwBwg0uK?type=png)](https://mermaid.live/edit#pako:eNp1k8Fu2zAMhl-F8GkDmj2ADwMSO0ndNkXQZjvM6YGVmUSLJRmSPLSr-zDDDgMK9Lgn8IuNlp2sGTAfDFv6f34UST1FwhQUxdHWYrWDVbrWwM84z3T7KqSJITFKSQ8aYWJRix0sUOo7GI0-Nisrt1uyDUzeTWpZFrz1jTQk2fs-yiSopg8kao8NJPmKnCcHn7T07Q8rjQOCgiDTnpjf_mp_mrvemgTrbS3IOdNAmk9VhcJ4VKS9AWFUDzuRz7DcMWeaXxsvN1KghbDUMXr0IE-DfGw9bZCjzcliwZRZvqzvS_aFTDrXQRKDEztjSrQjrOSHr2iHSLMuEszzpaUKhyNA9wUpVaV5HGTzvl7o9jD-XltKjPZcRrLjqnINnOe84LytBzBCpnBLClIj9nRgnQdW1pWitpYPN4gCbpzcDLIsyC7yPgHQpkfCkQkddBBfhMR4ayO39SH_gi3qXnKlqYFLHgUpJOO6AO1LF2EwXwbSVf7W_pv7y4W7IX4RTB8q4-SxnEtjPQ7mq2Be5J_Jhl4d0VPnuRt9EaoSOem3g7EICZ_swITUqBuVQhbc_ut8KSsqOc8uY1HW7WvRT8wwTieR-gHR_8D-M0THnkZnkSKrUBZ8dZ66eOvI70jROor5s0C7X0dr_cw6rL25fdQiirm_dBbVVYGeUok88SqKN1g6Xq1QfzHm7z-fxRu76O9muKLPfwBwg0uK](https://i.postimg.cc/zvPNc0pS/Whats-App-Image-2023-11-12-at-09-50-42-1.jpg))

### Conclusão
Este pipeline de CI/CD detalhado para a Schoolar API assegura uma integração e entrega eficientes, com foco na qualidade, segurança e confiabilidade do aplicativo.



</details>

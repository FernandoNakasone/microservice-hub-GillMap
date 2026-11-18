# GillMap — Global Solution 2026.2

## Integrantes

- Nome: **Fernando Massaji Nakasone** — RM: **558179**
- Nome: **Eduardo Arrivabene Caló** — RM: **555933**

## Problema e solução

O GillMap foi desenvolvido para registrar regiões e ocorrências de alagamento, permitindo acompanhar a situação de cada região conforme a altura da água informada.

A solução utiliza dois microsserviços: **MS Regiões**, responsável pelas regiões e suas situações, e **MS Alagamentos**, responsável pelos registros de alagamento. Ao cadastrar ou atualizar um alagamento, o MS Alagamentos se comunica com o MS Regiões para atualizar sua situação. A aplicação também conta com Eureka Server e API Gateway.

## Como executar e testar

**Pré-requisitos:** Java 25, Docker Desktop em execução, conexão com a internet para obter as dependências e as portas 3306, 8761 e 8082 disponíveis. Os comandos abaixo são para Windows/PowerShell.

## Configuração do Java 25

### Configuração do IntelliJ IDEA

Após instalar o JDK 25, abra os projetos do GillMap no IntelliJ IDEA e realize as configurações abaixo.

**1. Configurar Project SDK e Language Level**

1. Acesse **File → Project Structure** (`Ctrl + Alt + Shift + S`).
2. No menu lateral, selecione **Project**.
3. Em **SDK**, selecione **JDK 25**.
4. Em **Language level**, selecione **25** (sem Preview).
5. Clique em **Apply**.

**2. Configurar os Modules**

1. Ainda em **Project Structure**, selecione **Modules**.
2. Selecione o módulo do microsserviço.
3. Na aba **Sources**, configure **Language level: 25**.
4. Na aba **Dependencies**, configure **Module SDK: Project SDK (25)** ou selecione diretamente o JDK 25.
5. Clique em **Apply → OK**.

Repita essas configurações nos projetos **MS Regiões** e **MS Alagamentos**.

**3. Instalar ou habilitar o plugin Lombok**

O projeto utiliza Lombok para gerar automaticamente métodos como getters, setters e construtores.

1. Acesse **File → Settings** (`Ctrl + Alt + S`).
2. No menu lateral, selecione **Plugins**.
3. Na aba **Installed**, procure por `Lombok`.
4. Se estiver instalado e habilitado, não é necessário fazer nada.
5. Caso não esteja instalado, abra a aba **Marketplace**.
6. Pesquise por `Lombok`.
7. Clique em **Install**.
8. Caso solicitado, reinicie o IntelliJ IDEA.

**4. Habilitar Annotation Processing**

1. Acesse **File → Settings**.
2. Entre em **Build, Execution, Deployment → Compiler → Annotation Processors**.
3. Marque **Enable annotation processing**, caso ainda não esteja habilitado.
4. Clique em **Apply → OK**.

**5. Verificar a configuração do Maven**

1. Acesse **File → Settings**.
2. Entre em **Build, Execution, Deployment → Build Tools → Maven → Runner**.
3. Em **JRE**, selecione **Project JDK (25)**.
4. Clique em **Apply → OK**.

Após configurar o ambiente, recarregue os projetos Maven para que o IntelliJ reconheça as dependências.

### 1. Iniciar o MySQL no Docker

Após baixar o repositório, abra um terminal na raiz do projeto e execute:

```powershell
cd mysql_846
docker compose up -d
cd ..
```

O arquivo `mysql_846/docker-compose.yml` inicia o MySQL 8.4.6 na porta `3306`, com usuário `root` e senha `root`. Cada microsserviço utiliza seu próprio banco (`regioes` e `alagamentos`), criado automaticamente na inicialização com o perfil `dev`. O Flyway cria as tabelas e insere os registros iniciais.

### 2. Gerar os arquivos JAR

Na raiz do repositório, execute:

```powershell
cd eureka-server
.\mvnw.cmd clean package
cd ..

cd api-gateway
.\mvnw.cmd clean package
cd ..

cd ms-regioes
.\mvnw.cmd clean package
cd ..

cd ms-alagamentos
.\mvnw.cmd clean package
cd ..
```

### 3. Iniciar os componentes

Abra **quatro terminais separados**, cada um inicialmente na raiz do repositório. Execute os comandos abaixo na ordem indicada e mantenha os terminais abertos.

**Terminal 1 — Eureka Server**

```powershell
cd eureka-server
java -jar target\eureka-server-0.0.1-SNAPSHOT.jar
```

**Terminal 2 — API Gateway**

```powershell
cd api-gateway
java -jar target\api-gateway-0.0.1-SNAPSHOT.jar
```

**Terminal 3 — MS Regiões**

```powershell
cd ms-regioes
java -jar target\ms-regioes-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

**Terminal 4 — MS Alagamentos**

```powershell
cd ms-alagamentos
java -jar target\ms-alagamentos-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

O perfil `dev` ativa o MySQL e o Flyway, sem necessidade de editar os arquivos de configuração.

### 4. Testar a solução

Após iniciar todos os componentes, acesse:

- **Eureka Server:** http://localhost:8761 — verifique se o API Gateway e os dois microsserviços estão registrados como `UP`.
- **Swagger UI:** http://localhost:8082/swagger-ui/index.html — utilize a opção **Select a definition** para selecionar o microsserviço desejado.

Pelo Swagger, é possível executar as operações CRUD dos microsserviços e testar a comunicação entre MS Alagamentos e MS Regiões.
# Exemplo de implementação CORBA

Este projeto demonstra uma implementação simples da arquitetura CORBA 
(Common Object Request Broker Architecture), que permite a 
comunicação entre objetos distribuídos em uma rede, ou seja, 
invocar métodos remotos utilizando Java.

O projeto inclui a definição de uma interface CORBA (IDL), a
implementação de um servidor que registra o objeto remoto e um
cliente que invoca os métodos do servidor. o arquivo idl é exucutado 
utilizando o idlj da jdk para gerar os stubs e skeletons necessários.

## Estrutura do Projeto

- `src/` - Código-fonte do projeto, incluindo as classes `HelloServer` e `HelloClient`
- `Hello.idl` - Arquivo IDL que define a interface CORBA

## Requisitos

- Java 1.8 (JDK 8, pode ser a mais recente)
- CORBA (`tnameserv` ou `orbd`)

#### Nota: Programa feito no Windows 11

---

#### Notas: Caso as etapas posteriores apresentem erros, provavelmente será necessário modificar o PATH da JDK.
Isso pode ser feito de forma temporária ou permanente. Seguem os exemplos:
- Modifique para o seu caminho do bin da jdk.

#### Temporario:
```bash
$env:Path += ";C:\Program Files (x86)\Java\jdk1.8.0_202\bin"
```
#### Definitivo:
```bash
[System.Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Program Files (x86)\Java\jdk1.8.0_202\bin", [System.EnvironmentVariableTarget]::User)
```

## Como Compilar e Executar

### Passo 1: Compilar o IDL
```bash
idlj -fall Hello.idl
```

### Passo 2: Executar o serviço de nomes
```bash
tnameserv -ORBInitialPort 1050
```

### Passo 3: Executar o servidor (Na IDE)
```bash
java HelloServer
```

### Passo 4: Executar o cliente (Na IDE)
```bash
java HelloClient
```

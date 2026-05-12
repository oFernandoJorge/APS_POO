## 🏁 Sistema de Corridas de Rua – Fortaleza / CE

Sistema de gerenciamento de corridas de rua desenvolvido em Java como trabalho prático da disciplina de **Programação Orientada a Objetos** do curso de ADS.

O projeto aplica os quatro pilares de POO — encapsulamento, herança, polimorfismo e associação — para gerenciar eventos, corredores, patrocinadores e inscrições das principais corridas de rua de Fortaleza.

---

## 📋 Funcionalidades

- Cadastrar eventos, corredores e patrocinadores via terminal
- Inscrever corredores em eventos com validação de vagas e dupla inscrição
- Vincular patrocinadores a eventos
- Registrar tempo final de cada corredor após a prova
- Listar eventos, corredores, patrocinadores e inscrições
- **Bônus:** ranking por categoria com medalhas 🥇🥈🥉
- **Bônus:** relatório financeiro com receita de inscrições e patrocínios por evento

---

## 🏗️ Estrutura do Projeto

```
src/
└── br/com/corridasfortaleza/
    ├── Main.java                        # Ponto de entrada
    │
    ├── enums/
    │   ├── Categoria.java               # INICIANTE, AMADOR, PROFISSIONAL
    │   └── TipoParceria.java            # BRONZE, PRATA, OURO, DIAMANTE
    │
    ├── model/
    │   ├── Participante.java            # Classe abstrata base
    │   ├── Corredor.java                # Herda de Participante
    │   ├── Patrocinador.java            # Herda de Participante
    │   ├── EventoEsportivo.java         # Associação com Patrocinador
    │   └── Inscricao.java               # Associação Corredor ↔ Evento
    │
    ├── service/
    │   └── GerenciamentoEventos.java    # Regras de negócio e relatórios
    │
    ├── ui/
    │   └── Menu.java                    # Interface interativa via terminal
    │
    └── util/
        └── Formatador.java              # Helpers: tempo, moeda, separadores
```

---

## 🧱 Pilares de POO Aplicados

### Encapsulamento
Todos os atributos das classes são `private`, acessados e modificados apenas via getters e setters.

### Herança
`Corredor` e `Patrocinador` estendem a classe abstrata `Participante`, reaproveitando os atributos `nome`, `email` e `telefone` sem repetição de código.

```
Participante (abstrata)
├── Corredor
└── Patrocinador
```

### Polimorfismo
O método `exibirInformacoes()` é declarado como abstrato em `Participante` e implementado de forma diferente em cada subclasse — cada uma sabe como se apresentar.

### Associação
A classe `Inscricao` conecta `Corredor` e `EventoEsportivo`, formando uma relação muitos-para-muitos. `EventoEsportivo` mantém uma lista de `Patrocinador`.

---

## ▶️ Como Executar

### Pré-requisito
- Java 11 ou superior instalado

### Pelo terminal

```bash
# 1. Compile todos os arquivos
javac -d out -sourcepath src $(find src -name "*.java")

# 2. Execute
java -cp out br.com.corridasfortaleza.Main
```

### Pelo IntelliJ IDEA

1. Abra o IntelliJ e selecione **Open**
2. Navegue até a pasta do projeto e abra-a
3. Clique com o botão direito na pasta `src` → **Mark Directory as** → **Sources Root**
4. Abra o arquivo `Main.java`
5. Clique no botão ▶️ ao lado do método `main` para executar

---

## 🖥️ Exemplo de Uso

Ao iniciar, o sistema exibe o menu principal:

```
╔═══════════════════════════════════════════╗
║   🏁  CORRIDAS DE RUA – FORTALEZA / CE    ║
║       Sistema de Gerenciamento POO        ║
╚═══════════════════════════════════════════╝

╔═════════════════════════════════════════╗
║            MENU PRINCIPAL               ║
╠═════════════════════════════════════════╣
║  CADASTROS                              ║
║   1 · Cadastrar Evento                  ║
║   2 · Cadastrar Corredor                ║
║   3 · Cadastrar Patrocinador            ║
║   4 · Vincular Patrocinador a Evento    ║
╠═════════════════════════════════════════╣
║  OPERAÇÕES                              ║
║   5 · Inscrever Corredor em Evento      ║
║   6 · Registrar Tempo Final             ║
╠═════════════════════════════════════════╣
║  CONSULTAS                              ║
║   7 · Listar Eventos                    ║
║   8 · Listar Corredores                 ║
║   9 · Listar Patrocinadores             ║
║  10 · Listar Inscrições                 ║
╠═════════════════════════════════════════╣
║  RELATÓRIOS (BÔNUS)                     ║
║  11 · Ranking por Categoria             ║
║  12 · Relatório de Faturamento          ║
╠═════════════════════════════════════════╣
║   0 · Sair                              ║
╚═════════════════════════════════════════╝
```

---

## 📦 Ordem de Criação dos Arquivos

Seguindo a regra "crie o que não depende de ninguém primeiro":

```
1. enums/Categoria.java
2. enums/TipoParceria.java
3. model/Participante.java
4. model/Corredor.java
5. model/Patrocinador.java
6. util/Formatador.java
7. model/EventoEsportivo.java
8. model/Inscricao.java
9. service/GerenciamentoEventos.java
10. ui/Menu.java
11. Main.java
```

---

## 👨‍💻 Tecnologias

- Java 21
- Nenhuma dependência externa — apenas a biblioteca padrão do Java

---

## 📚 Disciplina

Programação Orientada a Objetos — ADS  
Contexto: Corridas de rua de Fortaleza (Corrida de São Sebastião, Corrida Noturna da Beira-Mar)
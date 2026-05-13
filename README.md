# 🎵 Sistema de Streaming de Música

## 📋 Funcionalidades
- Cadastro e gerenciamento de usuários Free e Premium
- Reproduzir músicas no catálogo
- Sistema de playlists personalizadas
- Playlist automática por gênero
- Histórico de reprodução por usuário
- Download de músicas para usuários Premium
- Recomendações por gênero
- Estatísticas de músicas mais reproduzidas
- Validação de dados com utilitários dedicados

## 🏗️ Arquitetura
- `br.com.streaming.modelo`: classes de domínio e herança (`ItemReproducao`, `Musica`, `Playlist`, `Usuario`, `UsuarioFree`, `UsuarioPremium`)
- `br.com.streaming.servico`: interfaces e serviços (`Reproduzivel`, `Baixavel`, `GeradorRecomendacoes`)
- `br.com.streaming.util`: utilitários de validação e formatação (`Validador`, `FormatadorTempo`)
- `br.com.streaming.principal`: classe principal de execução (`StreamingMusica`)

### Conceitos de POO aplicados
- Encapsulamento em campos e validação em setters/utilitários
- Herança em `UsuarioFree` e `UsuarioPremium` a partir de `Usuario`
- Polimorfismo em métodos `reproduzirMusica`, `reproduzir` e `criarPlaylist`
- Interfaces `Reproduzivel` e `Baixavel` implementadas
- Classe abstrata `ItemReproducao` compartilhando comportamento comum
- Uso de `@Override` em métodos sobrescritos

## 🚀 Como Executar
1. Navegue até a pasta do principal do projeto e execute para rodar o sistema.

## 👤 Autor
- Nome: Matheus Caetano Rocha
- RA: 45998400

## 📅 Histórico
- Checkpoint 1: Lógica, Métodos e Arrays
- Checkpoint 2: Classes e Objetos
- Checkpoint 3: Encapsulamento e Construtores
- Checkpoint 4: Herança, Sobrecarga e Sobrescrita
- Checkpoint 5: Polimorfismo e Classes Abstratas
- Checkpoint 6: Interfaces e Versão Final

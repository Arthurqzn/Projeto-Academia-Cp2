-- ==========================================================
-- Script T-SQL (SQL Server) para criar o schema manualmente
-- Necessario no profile PRD (ddl-auto=validate) ou quando DDL_AUTO=none
-- ==========================================================
IF DB_ID('treino_db') IS NULL
    CREATE DATABASE treino_db;
GO

USE treino_db;
GO

IF OBJECT_ID('dbo.treinos', 'U') IS NULL
CREATE TABLE dbo.treinos (
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome            VARCHAR(100)  NOT NULL,
    data_treino     DATE          NOT NULL,
    duracao_minutos INT           NOT NULL,
    tipo            VARCHAR(50)   NOT NULL,
    nivel           VARCHAR(20)   NOT NULL,
    observacoes     VARCHAR(1000) NULL
);
GO

IF OBJECT_ID('dbo.exercicios', 'U') IS NULL
CREATE TABLE dbo.exercicios (
    id             BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome           VARCHAR(100)  NOT NULL,
    grupo_muscular VARCHAR(50)   NOT NULL,
    series         INT           NOT NULL,
    repeticoes     INT           NOT NULL,
    carga_kg       FLOAT         NULL,
    descricao      VARCHAR(1000) NULL
);
GO

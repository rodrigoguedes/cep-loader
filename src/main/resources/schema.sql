CREATE TABLE IF NOT EXISTS cep_paises (
    pai_id SERIAL PRIMARY KEY,
    pai_sigla CHAR(2),
    pai_nome VARCHAR(255),
    CONSTRAINT con_unique_pai_sigla UNIQUE (pai_sigla)
);
CREATE INDEX IF NOT EXISTS idx_cep_paises_pai_sigla ON cep_paises(pai_sigla);
CREATE INDEX IF NOT EXISTS idx_cep_paises_pai_nome ON cep_paises(pai_nome);

CREATE TABLE IF NOT EXISTS cep_estados (
    est_id SERIAL PRIMARY KEY,
    est_sigla CHAR(2),
    est_nome VARCHAR(255),
    pai_id INT,
    FOREIGN KEY (pai_id) REFERENCES cep_paises,
    CONSTRAINT con_unique_est_sigla UNIQUE (est_sigla, pai_id)
);
CREATE INDEX IF NOT EXISTS idx_cep_estados_est_sigla ON cep_estados(est_sigla);
CREATE INDEX IF NOT EXISTS idx_cep_estados_est_nome ON cep_estados(est_nome);

CREATE TABLE IF NOT EXISTS cep_tipos_logradouro (
    tpl_id SERIAL PRIMARY KEY,
    tpl_codigo INT,
    tpl_nome VARCHAR(255),
    tpl_abreviacao VARCHAR(255),
    CONSTRAINT con_unique_tpl_codigo UNIQUE (tpl_codigo)
);
CREATE INDEX IF NOT EXISTS idx_tipos_logradouro_tpl_codigo ON cep_tipos_logradouro(tpl_codigo);
CREATE INDEX IF NOT EXISTS idx_tipos_logradouro_tpl_nome ON cep_tipos_logradouro(tpl_nome);

CREATE TABLE IF NOT EXISTS cep_cidades (
    cid_id SERIAL PRIMARY KEY,
    cid_codigo INT,
    cid_codigo_ibge INT,
    cid_nome VARCHAR(255),
    cid_cep CHAR(8),
    cid_abreviacao VARCHAR(255),
    cid_tipo_localidade CHAR(1),
    est_id INT,
    FOREIGN KEY (est_id) REFERENCES cep_estados,
    CONSTRAINT con_unique_cid_codigo UNIQUE (est_id, cid_codigo)
);
CREATE INDEX IF NOT EXISTS idx_cidades_cid_codigo ON cep_cidades(cid_codigo);
CREATE INDEX IF NOT EXISTS idx_cidades_cid_codigo_ibge ON cep_cidades(cid_codigo, cid_codigo_ibge);
CREATE INDEX IF NOT EXISTS idx_cidades_cid_nome ON cep_cidades(cid_nome);
CREATE INDEX IF NOT EXISTS idx_cidades_estado_nome ON cep_cidades(est_id, cid_nome);

CREATE TABLE IF NOT EXISTS cep_bairros (
    bai_id SERIAL PRIMARY KEY,
    bai_codigo INT,
    bai_nome VARCHAR(255),
    bai_abreviacao VARCHAR(255),
    cid_id INT,
    FOREIGN KEY (cid_id) REFERENCES cep_cidades,
    CONSTRAINT con_unique_bai_codigo UNIQUE (cid_id, bai_codigo)
);
CREATE INDEX IF NOT EXISTS idx_bairros_bai_codigo ON cep_bairros(bai_codigo);
CREATE INDEX IF NOT EXISTS idx_bairros_bai_nome ON cep_bairros(bai_nome);
CREATE INDEX IF NOT EXISTS idx_bairros_cidade_nome ON cep_bairros(cid_id, bai_nome);

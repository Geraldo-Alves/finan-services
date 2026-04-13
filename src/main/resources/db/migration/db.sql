CREATE TABLE "categoria" (
  "id" serial PRIMARY KEY,
  "nome" varchar(100),
  "is_parcelavel" boolean,
  "is_fixo" boolean,
  "tipo" varchar(1)
);

CREATE TABLE "valor" (
  "id" serial PRIMARY KEY,
  "nome" varchar(100),
  "descricao" varchar(100),
  "valor" float,
  "parcela" integer,
  "qtd_parcelas" integer,
  "ref_id_categoria" integer,
  "mes" integer,
  "ano" integer,
  "status" varchar(1),
  "created_at" timestamp DEFAULT (current_timestamp)
);

ALTER TABLE "valor" ADD FOREIGN KEY ("ref_id_categoria") REFERENCES "categoria" ("id") DEFERRABLE INITIALLY IMMEDIATE;

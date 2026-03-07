CREATE EXTENSION IF NOT EXISTS ltree;

CREATE TABLE folders (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    parent_id UUID,
    path LTREE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_folder_parent
        FOREIGN KEY(parent_id)
        REFERENCES folders(id)
        ON DELETE CASCADE
);

CREATE TABLE files (
    id UUID PRIMARY KEY,
    folder_id UUID,
    filename TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_files_folder
        FOREIGN KEY(folder_id)
        REFERENCES folders(id)
        ON DELETE SET NULL
);

CREATE TABLE objects (
    id UUID PRIMARY KEY,
    storage_key TEXT NOT NULL,
    size BIGINT NOT NULL,
    content_type TEXT,
    checksum TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT unique_storage_key
        UNIQUE(storage_key)
);

CREATE TABLE file_versions (
    id UUID PRIMARY KEY,
    file_id UUID NOT NULL,
    version_number INT NOT NULL,
    object_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_versions_file
        FOREIGN KEY(file_id)
        REFERENCES files(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_versions_object
        FOREIGN KEY(object_id)
        REFERENCES objects(id)
        ON DELETE RESTRICT
);

CREATE INDEX idx_files_folder
ON files(folder_id);

CREATE INDEX idx_folder_path
ON folders USING GIST(path);

CREATE INDEX idx_versions_file
ON file_versions(file_id);

CREATE INDEX idx_versions_object
ON file_versions(object_id);

CREATE INDEX idx_objects_checksum
ON objects(checksum);

ALTER TABLE file_versions
ADD CONSTRAINT unique_file_version
UNIQUE(file_id, version_number);
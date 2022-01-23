/*
 * Copyright (c) 2018-2021 Tobias Briones. All rights reserved.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 *
 * This file is part of JDesk.
 *
 * This source code is licensed under the BSD-3-Clause License found in the
 * LICENSE file in the root directory of this source tree or at
 * https://opensource.org/licenses/BSD-3-Clause.
 */

package engineer.mathsoftware.jdesk.io;

/**
 * Defines general common file extensions.
 *
 * @author Tobias Briones
 */
public enum FileFormat {
    PLAIN_TEXT_TXT("txt"),
    PLAIN_CONFIG_CONFIG("config"),
    PLAIN_PROPERTIES_PROPERTIES("properties"),
    DATA_DAT("dat"),
    DATA_KEY("key"),
    DATA_CSV("csv"),
    DATA_XML("xml"),
    DATA_JSON("json"),
    HTML("html"),
    DOC_WORD_DOCX("docx"),
    DOC_EXCEL_XLSX("xlsx"),
    DOC_POWER_POINT_PPT("ppt"),
    DOC_PDF("pdf"),
    CODE_JAVA("java"),
    CODE_PHP("php"),
    CODE_CPP("cpp"),
    CODE_CS("cs"),
    DB_SQL("sql"),
    WIN_BAT("bat"),
    WIN_EXE("exe"),
    JAVA_JAR("jar"),
    IMAGE_PNG("png"),
    IMAGE_JPG("jpg"),
    IMAGE_BMP("bmp"),
    AUDIO_MP3("mp3"),
    AUDIO_WAV("wav"),
    AUDIO_WMA("wma"),
    VIDEO_MP4("mp4"),
    VIDEO_MKV("mkv"),
    VIDEO_AVI("avi"),
    VIDEO_FLV("flv"),
    VIDEO_MOV("mov"),
    VIDEO_3GP("3gp"),
    COMPRESS_ZIP("zip"),
    COMPRESS_RAR("rar"),
    FONT_TTF("ttf");

    private final String extension;

    FileFormat(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return extension;
    }
}

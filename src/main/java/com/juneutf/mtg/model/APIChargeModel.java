package com.juneutf.mtg.model;

import lombok.Data;

/**
 * APIの担当者情報を表します。
 */
@Data
public class APIChargeModel {
    /**
     * 担当者ID。
     */
    private int id;

    /**
     * 担当者名です。
     */
    private String charge;
}

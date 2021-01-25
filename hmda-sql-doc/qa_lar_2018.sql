-- Table: hmda_user.qa_lar_table_2018

-- DROP TABLE hmda_user.qa_lar_table_2018;

CREATE TABLE hmda_user.qa_lar_table_2018
(
    id integer,
    lei character varying COLLATE pg_catalog."default",
    uli character varying COLLATE pg_catalog."default",
    application_date character varying COLLATE pg_catalog."default",
    loan_type integer,
    loan_purpose integer,
    preapproval integer,
    construction_method character varying COLLATE pg_catalog."default",
    occupancy_type integer,
    loan_amount numeric,
    action_taken_type integer,
    action_taken_date integer,
    street character varying COLLATE pg_catalog."default",
    city character varying COLLATE pg_catalog."default",
    state character varying COLLATE pg_catalog."default",
    zip character varying COLLATE pg_catalog."default",
    county character varying COLLATE pg_catalog."default",
    tract character varying COLLATE pg_catalog."default",
    ethnicity_applicant_1 character varying COLLATE pg_catalog."default",
    ethnicity_applicant_2 character varying COLLATE pg_catalog."default",
    ethnicity_applicant_3 character varying COLLATE pg_catalog."default",
    ethnicity_applicant_4 character varying COLLATE pg_catalog."default",
    ethnicity_applicant_5 character varying COLLATE pg_catalog."default",
    other_hispanic_applicant character varying COLLATE pg_catalog."default",
    ethnicity_co_applicant_1 character varying COLLATE pg_catalog."default",
    ethnicity_co_applicant_2 character varying COLLATE pg_catalog."default",
    ethnicity_co_applicant_3 character varying COLLATE pg_catalog."default",
    ethnicity_co_applicant_4 character varying COLLATE pg_catalog."default",
    ethnicity_co_applicant_5 character varying COLLATE pg_catalog."default",
    other_hispanic_co_applicant character varying COLLATE pg_catalog."default",
    ethnicity_observed_applicant integer,
    ethnicity_observed_co_applicant integer,
    race_applicant_1 character varying COLLATE pg_catalog."default",
    race_applicant_2 character varying COLLATE pg_catalog."default",
    race_applicant_3 character varying COLLATE pg_catalog."default",
    race_applicant_4 character varying COLLATE pg_catalog."default",
    race_applicant_5 character varying COLLATE pg_catalog."default",
    other_native_race_applicant character varying COLLATE pg_catalog."default",
    other_asian_race_applicant character varying COLLATE pg_catalog."default",
    other_pacific_race_applicant character varying COLLATE pg_catalog."default",
    race_co_applicant_1 character varying COLLATE pg_catalog."default",
    race_co_applicant_2 character varying COLLATE pg_catalog."default",
    race_co_applicant_3 character varying COLLATE pg_catalog."default",
    race_co_applicant_4 character varying COLLATE pg_catalog."default",
    race_co_applicant_5 character varying COLLATE pg_catalog."default",
    other_native_race_co_applicant character varying COLLATE pg_catalog."default",
    other_asian_race_co_applicant character varying COLLATE pg_catalog."default",
    other_pacific_race_co_applicant character varying COLLATE pg_catalog."default",
    race_observed_applicant integer,
    race_observed_co_applicant integer,
    sex_applicant integer,
    sex_co_applicant integer,
    observed_sex_applicant integer,
    observed_sex_co_applicant integer,
    age_applicant integer,
    age_co_applicant integer,
    income character varying COLLATE pg_catalog."default",
    purchaser_type integer,
    rate_spread character varying COLLATE pg_catalog."default",
    hoepa_status integer,
    lien_status integer,
    credit_score_applicant integer,
    credit_score_co_applicant integer,
    credit_score_type_applicant integer,
    credit_score_model_applicant character varying COLLATE pg_catalog."default",
    credit_score_type_co_applicant integer,
    credit_score_model_co_applicant character varying COLLATE pg_catalog."default",
    denial_reason1 character varying COLLATE pg_catalog."default",
    denial_reason2 character varying COLLATE pg_catalog."default",
    denial_reason3 character varying COLLATE pg_catalog."default",
    denial_reason4 character varying COLLATE pg_catalog."default",
    other_denial_reason character varying COLLATE pg_catalog."default",
    total_loan_costs character varying COLLATE pg_catalog."default",
    total_points character varying COLLATE pg_catalog."default",
    origination_charges character varying COLLATE pg_catalog."default",
    discount_points character varying COLLATE pg_catalog."default",
    lender_credits character varying COLLATE pg_catalog."default",
    interest_rate character varying COLLATE pg_catalog."default",
    payment_penalty character varying COLLATE pg_catalog."default",
    debt_to_incode character varying COLLATE pg_catalog."default",
    loan_value_ratio character varying COLLATE pg_catalog."default",
    loan_term character varying COLLATE pg_catalog."default",
    rate_spread_intro character varying COLLATE pg_catalog."default",
    baloon_payment integer,
    insert_only_payment integer,
    amortization integer,
    other_amortization integer,
    property_value character varying COLLATE pg_catalog."default",
    home_security_policy integer,
    lan_property_interest integer,
    total_uits integer,
    mf_affordable character varying COLLATE pg_catalog."default",
    application_submission integer,
    payable integer,
    nmls character varying COLLATE pg_catalog."default",
    aus1 character varying COLLATE pg_catalog."default",
    aus2 character varying COLLATE pg_catalog."default",
    aus3 character varying COLLATE pg_catalog."default",
    aus4 character varying COLLATE pg_catalog."default",
    aus5 character varying COLLATE pg_catalog."default",
    other_aus character varying COLLATE pg_catalog."default",
    aus1_result integer,
    aus2_result character varying COLLATE pg_catalog."default",
    aus3_result character varying COLLATE pg_catalog."default",
    aus4_result character varying COLLATE pg_catalog."default",
    aus5_result character varying COLLATE pg_catalog."default",
    other_aus_result character varying COLLATE pg_catalog."default",
    reverse_mortgage integer,
    line_of_credits integer,
    business_or_commercial integer,
    conforming_loan_limit character varying COLLATE pg_catalog."default",
    ethnicity_categorization character varying COLLATE pg_catalog."default",
    race_categorization character varying COLLATE pg_catalog."default",
    sex_categorization character varying COLLATE pg_catalog."default",
    dwelling_categorization character varying COLLATE pg_catalog."default",
    loan_product_type_categorization character varying COLLATE pg_catalog."default",
    tract_population integer,
    tract_minority_population_percent double precision,
    ffiec_msa_md_median_family_income integer,
    tract_owner_occupied_units integer,
    tract_one_to_four_family_homes integer,
    tract_median_age_of_housing_units integer,
    tract_to_msa_income_percentage double precision,
    is_quarterly boolean,
    created_at timestamp without time zone,
    msa_md character varying COLLATE pg_catalog."default",
    msa_md_name character varying COLLATE pg_catalog."default",
    checksum character varying COLLATE pg_catalog."default",
    file_name character varying COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE hmda_user.qa_lar_table_2018
    OWNER to hmda_user;
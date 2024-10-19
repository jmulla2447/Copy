CREATE
OR
REPLACE
FUNCTION handle_query(
    query_type TEXT,
    param1 TEXT,
    param2 DOUBLE PRECISION DEFAULT NULL
)
RETURNS TABLE(
    result TEXT
) AS $$
BEGIN
    -- Procedure logic goes here...
    -- You can implement logic for SYMBOL, COUNT, GAIN, etc.
    -- Example:
    IF query_type = 'SYMBOL' THEN
        RETURN QUERY
        SELECT symbol || ',' || series || ',' || close_price || ',' || open_price
        FROM csv_records
        WHERE symbol = param1;

    ELSIF query_type = 'COUNT' THEN
        RETURN QUERY
        SELECT COUNT(*)
        FROM csv_records
        WHERE series = param1;

    -- Add additional logic here for GAIN, TOPBOT, STDDEV

    END IF;
END;
$$ LANGUAGE plpgsql;

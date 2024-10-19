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

    ELSIF query_type = 'GAIN' THEN
        RETURN QUERY
        SELECT STRING_AGG(symbol, ',')
        FROM csv_records
        WHERE ((close_price - open_price) / open_price) * 100 > param2;

    ELSIF query_type = 'TOPBOT' THEN
        RETURN QUERY
        SELECT STRING_AGG(symbol, ',')
        FROM csv_records
        WHERE ((high_price - low_price) / low_price) * 100 > param2;

    END IF;
END;
$$ LANGUAGE plpgsql;

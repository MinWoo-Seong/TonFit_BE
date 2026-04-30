INSERT INTO users (email)
VALUES ('test@example.com')
ON CONFLICT (email) DO NOTHING;

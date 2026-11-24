-- Execute manualmente apenas no banco local de demonstração após /api/auth/register.
-- Substitua pelo e-mail da conta controlada usada na collection.
UPDATE usuarios SET perfil = 'ADMIN' WHERE email = '<email-controlado>';
